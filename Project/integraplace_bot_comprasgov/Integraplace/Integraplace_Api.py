import requests

from Environments.Variables import api_uri


def get_all_editais(auth):
    uri = f'{api_uri}/Edital/allbysystemname/Comprasgov'

    headers = {
        'Authorization': auth["access_token"]
    }

    try:
        response = requests.get(uri, headers=headers, timeout=10)
        if response.status_code == 200:
            return response.json()
        else:
            return []
    except Exception as e:
        print(f'Error get_all_editais: {e}')
