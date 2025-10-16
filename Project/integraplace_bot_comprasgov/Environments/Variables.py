import os
from dotenv import load_dotenv

load_dotenv()

keycloak = {
    'url': os.getenv('KC_HOST'),
    'realm': os.getenv('KC_CLIENT_ID'),
    'clientid': os.getenv('BOT_CLIENTID'),
    'grant_type': 'password',
    'user': os.getenv('BOT_USER'),
    'password': os.getenv('BOT_PASSWORD')
}

api_uri = os.getenv('API_HOST')
