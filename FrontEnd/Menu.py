import flet as ft
from Login.Registro.Login import login_page
def main(page: ft.Page):


    def on_login():
        page.clean()
        page.add(login_page())

    on_login()
ft.app(target=main)