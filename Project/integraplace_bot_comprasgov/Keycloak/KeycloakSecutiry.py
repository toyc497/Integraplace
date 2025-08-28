import requests

from Environments.Variables import Variables


class KeycloakSecutiry:
    def logout(self, auth_data):
        uri = f'{Variables.keycloak["url"]}/realms/{Variables.keycloak["realm"]}/protocol/openid-connect/logout'

        headers = {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Authorization': auth_data["access_token"]
        }

        body = {
            'client_id': Variables.keycloak["clientid"],
            'refresh_token': auth_data["refresh_token"]
        }

        try:
            requests.post(uri, headers=headers, data=body, timeout=10)

        except Exception as e:
            print(f'Error in access_token: {e}')

    def get_acess_token(self):
        uri = f'{Variables.keycloak["url"]}/realms/{Variables.keycloak["realm"]}/protocol/openid-connect/token'

        headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        }

        body = {
            'client_id': Variables.keycloak["clientid"],
            'username': Variables.keycloak["user"],
            'password': Variables.keycloak["password"],
            'grant_type': Variables.keycloak["grant_type"],
        }

        try:
            response = requests.post(uri, headers=headers, data=body, timeout=10)
            if response.status_code == 200:
                return {
                    'access_token': f'Bearer {response.json()["access_token"]}',
                    'refresh_token': response.json()["refresh_token"]
                }

        except Exception as e:
            print(f'Error in access_token: {e}')
