import flet as ft


def menu_page(on_login, on_listUsuario, on_editarUsuario):
    
    return ft.Container(
        content=ft.Column(
            [
                ft.Image(
                    src="C:/Users/João Vitor/IdeaProjects/Symple-Door/FrontEnd/Imagens/Logo.png",
                    width=300,  
                    height=300,  
                    fit=ft.ImageFit.CONTAIN,  
                ),

                ft.CupertinoButton(
                    content=ft.Text("Listar Usuario", color="#964b00", font_family="MinhaFonte", size=25),
                    width=400, height=55, bgcolor="#ffa761", on_click=on_listUsuario
                ),
                ft.CupertinoButton(
                    content=ft.Text("Editar Usuario", color="#964b00", font_family="MinhaFonte", size=25),
                    width=400, height=55, bgcolor="#ffa761", on_click=on_editarUsuario
                ),

                ft.Divider(thickness=20, color="transparent", height=100),

                ft.CupertinoButton(
                    content=ft.Text("Voltar", color="#964b00", font_family="MinhaFonte", size=25),
                    width=400, height=55, bgcolor="#ffa761", on_click=on_login
                ),

            ],
            alignment=ft.MainAxisAlignment.START,  
            horizontal_alignment=ft.CrossAxisAlignment.CENTER,
            spacing=18,  
        ),
        width=500,
        height=700,
        padding=20,
        border_radius=ft.border_radius.all(50),
        alignment=ft.alignment.center,
        image_src="C:/Users/João Vitor/IdeaProjects/Symple-Door/FrontEnd/Imagens/FundoMenu.png"
    )