import gc
from datetime import datetime
from time import sleep

import requests
import undetected_chromedriver as uc

from Comprasgov.Comprasgov_Api import get_home_page, get_hcaptcha_id, refresh_hcaptcha, get_chat
from Environments.Variables import api_uri
from Integraplace.Integraplace_Api import get_all_editais
from Keycloak.Keycloak_Security import get_acess_token

loop_delay = 30


def send_massive_messages(message_list, auth):
    uri = f'{api_uri}/Message/save'

    headers = {
        'Content-Type': 'application/json',
        'Authorization': auth["access_token"]
    }

    body = {
        'messageList': message_list
    }

    try:
        response = requests.post(uri, headers=headers, json=body, timeout=15)
        if response.status_code == 201:
            print('Messages API Send Successfull: ', len(message_list))

    except Exception as e:
        print(f'Erro in Messages API: {e}')


def messages_date(edital, message_list):
    messages_list_aux = []
    last_data = None

    if edital['last_date'] is not None:
        last_data = datetime.strptime(edital['last_date'][:19], '%Y-%m-%dT%H:%M:%S')

    for message in message_list:
        data = message['dataHora'][0:19]
        correct_data = datetime.strptime(data, '%Y-%m-%d %H:%M:%S')

        if last_data is None or correct_data > last_data:
            itemnum_aux = ''
            remetente_type = ''
            message_aux = ''

            if 'identificadorItem' in message:
                itemnum_aux = f'Item {message["identificadorItem"]} - '

            if message["tipoRemetente"] == '0':
                remetente_type = 'Sistema'
            elif message["tipoRemetente"] == '3':
                remetente_type = f'Comprador'
            elif message["tipoRemetente"] == '1':
                remetente_type = f'Participante'

            if 'identificadorDestinatario' in message:
                message_aux = f'Para {message["identificadorDestinatario"]} - '

            if 'identificadorRemetente' in message:
                message_aux = f'De {message["identificadorRemetente"]} - '

            messages_list_aux.append({
                'content': f'{itemnum_aux}{message_aux}{message["texto"]}',
                'origin': remetente_type,
                'message_date': f'{correct_data.isoformat()}',
                'read': False,
                'edital_id': edital["edital"]["id"]
            })

    return messages_list_aux


def setup_driver():
    options = uc.ChromeOptions()
    options.add_argument("--window-size=1920,1080")
    options.add_argument("--disable-blink-features=AutomationControlled")
    options.add_argument("--disable-infobars")
    options.add_argument("--disable-popup-blocking")
    options.add_argument("--incognito")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")
    options.add_argument('--headlessmode')
    options.add_argument('--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36')
    options.add_argument("--disable-gpu")
    options.add_argument("--disable-extensions")
    options.add_argument("--profile-directory=Default")
    options.add_argument("--disable-plugins-discovery")
    options.add_argument("--start-maximized")
    options.add_argument("--ignore-certificate-errors")
    options.add_argument("--allow-running-insecure-content")
    options.accept_insecure_certs = True

    driver = uc.Chrome(options=options)
    driver.implicitly_wait(5)
    driver.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
        "source": "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
    })

    driver.maximize_window()

    return driver


def execution():
    auth_variables = get_acess_token()
    editais_list = get_all_editais(auth_variables)

    if editais_list is not None:
        try:
            driver = setup_driver()
            driver_home_page = get_home_page(driver)
            driver_hcaptcha = get_hcaptcha_id(driver_home_page)

            if driver_hcaptcha is not None:
                for edital in editais_list:
                    try:
                        hcaptcha_code = refresh_hcaptcha(driver_home_page, driver_hcaptcha)
                        messages_response = get_chat(edital["edital"]["identifier"], hcaptcha_code)

                        if messages_response is not None and len(messages_response) > 0:
                            messages_response.reverse()
                            messages_list_send = messages_date(edital, messages_response)

                            if messages_list_send is not None and len(messages_list_send) > 0:
                                send_massive_messages(messages_list_send, auth_variables)
                                del messages_list_send

                        del messages_response
                        gc.collect()
                    except Exception as e:
                        print(f'Erro na execução do edital: {e}')

            driver.quit()

        except Exception as e:
            print(f'Erro no Webdriver: {e}')

    del auth_variables
    del editais_list
    gc.collect()


if __name__ == '__main__':
    while True:
        print('Executing')
        execution()
        sleep(loop_delay)
