import flet as ft
from Login.Registro.Login import login_page
from Login.Registro.Registro import registro_page
from Menu import menu_page
from OpcoesMenu.ListarUsuarios import listUsuario_page

def main(page: ft.Page):

    page.title = "Symple Door"
    page.bgcolor = "#000000"
    page.vertical_alignment = ft.MainAxisAlignment.CENTER
    page.horizontal_alignment = ft.CrossAxisAlignment.CENTER

    def on_listUsuario(event):
        page.clean()
        page.add(listUsuario_page(on_login))

    def on_menu(event):
        page.clean()
        page.add(menu_page(on_login, on_listUsuario))
        
    def on_registro(event):
        page.clean()
        page.add(registro_page(on_login))

    def on_login(event=None):
        page.clean()
        page.add(login_page(on_registro, on_menu))

    on_login()
ft.app(target=main)