import flet as ft
import requests
import time

def menu_page(on_login):
    
    return ft.Container(
        content=ft.Column(
            [
                ft.Image(
                    src="C:/Users/João Vitor/IdeaProjects/CarteiraAcao/FrontEnd/Imagens/MenuLogo.png",  
                    width=300,  
                    height=300,  
                    fit=ft.ImageFit.CONTAIN,  
                ),

                ft.CupertinoButton(
                    content=ft.Text("Listar Usuario", color="#f7931a", font_family="MinhaFonte", size=25),
                    width=400, height=55, bgcolor="#ffcb8c", 
                ),
                ft.CupertinoButton(
                    content=ft.Text("Adicionar Usuario", color="#f7931a", font_family="MinhaFonte", size=25),
                    width=400, height=55, bgcolor="#ffcb8c", 
                ),

            ],
            alignment=ft.MainAxisAlignment.START,  # Alinha os elementos ao topo
            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            spacing=18,  # Ajuste o espaçamento conforme necessário
        ),
        width=500,
        height=700,
        padding=20,
        border_radius=ft.border_radius.all(50),
        alignment=ft.alignment.center,
        image_src="C:/Users/João Vitor/IdeaProjects/CarteiraAcao/FrontEnd/Imagens/FundoMenu.png"
    )