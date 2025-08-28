from datetime import datetime
from time import sleep

import requests
import undetected_chromedriver as uc

from Comprasgov.ComprasgovAPI import ComprasgovAPI
from Environments.Variables import Variables
from Integraplace.IntegraplaceAPI import IntegraplaceAPI
from Keycloak.KeycloakSecutiry import KeycloakSecutiry


class Main:
    AUTH_VARIABLES = None
    loop_delay = 60

    def send_massive_messages(self, message_list):
        uri = f'{Variables.api_uri}/Message/save'

        headers = {
            'Content-Type': 'application/json',
            'Authorization': self.AUTH_VARIABLES["access_token"]
        }

        body = {
            'messageList': message_list
        }

        try:
            response = requests.post(uri, headers=headers, json=body, timeout=15)
            if response.status_code == 201:
                print('Messages API Successfull')

        except Exception as e:
            print(f'Erro in Messages API: {e}')

    def messages_date(self, edital, message_list):
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


    def setup_driver(self):
        options = uc.ChromeOptions()
        options.add_argument("--window-size=1920,1080")
        options.add_argument("--disable-blink-features=AutomationControlled")
        options.add_argument("--disable-infobars")
        options.add_argument("--disable-popup-blocking")
        options.add_argument("--incognito")
        options.add_argument("--no-sandbox")
        options.add_argument("--disable-dev-shm-usage")
        options.add_argument('--headlessmode')
        options.add_argument(
            '--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36')
        options.add_argument("--disable-gpu")
        options.add_argument("--disable-extensions")
        options.add_argument("--profile-directory=Default")
        options.add_argument("--disable-plugins-discovery")
        options.add_argument("--start-maximized")
        options.add_argument("--ignore-certificate-errors")
        options.add_argument("--allow-running-insecure-content")
        options.accept_insecure_certs = True

        driver = uc.Chrome(options=options)
        driver.implicitly_wait(10)
        driver.execute_cdp_cmd("Page.addScriptToEvaluateOnNewDocument", {
            "source": "Object.defineProperty(navigator, 'webdriver', {get: () => undefined})"
        })

        driver.maximize_window()

        return driver

    def execution(self):
        self.AUTH_VARIABLES = KeycloakSecutiry.get_acess_token(self)
        editais_list = IntegraplaceAPI.get_all_editais(self, self.AUTH_VARIABLES)

        if editais_list is not None:
            try:
                driver = self.setup_driver()
                driver_home_page = ComprasgovAPI.get_home_page(self, driver)
                driver_hcaptcha = ComprasgovAPI.get_hcaptcha_id(self, driver_home_page)

                for edital in editais_list:
                    try:
                        hcaptcha_code = ComprasgovAPI.refresh_hcaptcha(self, driver_home_page, driver_hcaptcha)
                        messages_response = ComprasgovAPI.get_chat(self, edital["edital"]["identifier"], hcaptcha_code)

                        if messages_response is not None and len(messages_response) > 0:
                            messages_response.reverse()
                            messages_list_send = self.messages_date(edital, messages_response)

                            if messages_list_send is not None and len(messages_list_send) > 0:
                                self.send_massive_messages(messages_list_send)

                    except Exception as e:
                        print(f'Erro na execução do edital: {e}')

            except Exception as e:
                print(f'Erro no Webdriver: {e}')


if __name__ == '__main__':
    main = Main()
    while True:
        print('Executing')
        main.execution()
        sleep(main.loop_delay)
