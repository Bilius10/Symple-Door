import flet as ft
from Login.Registro.Login import login_page
def main(page: ft.Page):

    page.title = "Symple Door"
    page.bgcolor = "#000000"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER

    def on_login():
        page.clean()
        page.add(login_page())

    on_login()
ft.app(target=main)