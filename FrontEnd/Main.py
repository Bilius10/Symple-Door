import flet as ft
from Login.Registro.Login import login_page
from Login.Registro.Registro import registro_page
from Menu import menu_page
from OpcoesMenu.ListarUsuarios import listUsuario_page
from OpcoesMenu.EditarUsuario import editarUsuario_page
from session import session


def main(page: ft.Page):

    page.title = "Symple Door"
    page.bgcolor = "#000000"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER

    session.user_data['url'] = input("Url: ")

    def on_editarUsuario(event):
        page.clean()
        page.add(editarUsuario_page(on_menu))

    def on_listUsuario(event):
        page.clean()
        page.add(listUsuario_page(on_menu))

    def on_menu(event):
        page.clean()
        page.add(menu_page(on_login, on_listUsuario, on_editarUsuario))

    def on_registro(event):
        page.clean()
        page.add(registro_page(on_login))

    def on_login(event=None):
        page.clean()
        page.add(login_page(on_registro, on_menu))

    on_login()


ft.app(target=main)
