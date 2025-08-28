from time import sleep

import requests
from bs4 import BeautifulSoup


class ComprasgovAPI:
    def get_chat(self, identifier, captcha):
        uri = f'https://cnetmobile.estaleiro.serpro.gov.br/comprasnet-mensagem/v2/chat/{identifier}?size=500&page=0&legadoAsp=false&captcha={captcha}'

        headers = {
            'User-Agent': 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Mobile Safari/537.36'
        }

        try:
            response = requests.get(uri, headers=headers, verify=True, timeout=15)
            if response.status_code == 200 or response.status_code == 206:
                return response.json()

            elif response.status_code == 204:
                return []

            else:
                print(f'StatusCode Chat API: {response.status_code} | Id: {identifier}')
                return []

        except Exception as e:
            print(f'Error Chat API: {e}')

    def refresh_hcaptcha(self, driver, hcaptcha_id):
        driver.execute_script(f"return hcaptcha.execute('{hcaptcha_id}')")
        sleep(3)
        hcaptcha_solved = driver.execute_script(f"return hcaptcha.getResponse('{hcaptcha_id}')")
        return hcaptcha_solved

    def get_hcaptcha_id(self, driver):
        soup = BeautifulSoup(driver.page_source, 'html.parser')
        element_id_hcaptcha = soup.find(id='id-hcaptcha')

        iframe = element_id_hcaptcha.find('iframe', {'data-hcaptcha-widget-id': True})
        if iframe:
            return iframe['data-hcaptcha-widget-id']
        else:
            return ''

    def get_home_page(self, driver):
        uri = 'https://cnetmobile.estaleiro.serpro.gov.br/comprasnet-web/public/compras'
        driver.get(uri)
        sleep(5)
        return driver

