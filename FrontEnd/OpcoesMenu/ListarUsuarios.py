import requests
import flet as ft
from session import session

def listUsuario_page(on_menu):
    headers = {"Authorization": "Bearer " + session.user_data.get("token", "")}
    
    try:
        response = requests.get("https://c5bc-177-93-150-55.ngrok-free.app/credenciais/todos", headers=headers)
        
        response.raise_for_status()  
        data = response.json()
        
    except requests.exceptions.RequestException as e:
        data = []
        print(f"Erro ao buscar usuários: {e}")

    columns = [
        ft.DataColumn(ft.Text("idCredencial")),
        ft.DataColumn(ft.Text("Nome")),
        ft.DataColumn(ft.Text("Senha"))
    ]

    rows = [
        ft.DataRow(
            cells=[
                ft.DataCell(ft.Text(credencial["idCredencial"])),
                ft.DataCell(ft.Text(credencial["nome"])),
                ft.DataCell(ft.Text(credencial["senha"]))
            ]
        ) for credencial in data
    ]

    data_table = ft.DataTable(columns=columns, rows=rows, border=ft.border.all(1))

    return ft.Container(
        content=ft.Column(
            [   
                ft.Text(
                    value="Lista de Usuarios", color="#ed8200", font_family="MinhaFonte", size=80
                ),

                ft.Column([data_table], scroll=ft.ScrollMode.ALWAYS, scheight=130),

                ft.CupertinoButton(
                    content=ft.Text("Voltar", color="#ed8200", font_family="MinhaFonte", size=20), width=150, height=55, 
                    on_click=on_menu
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
        image_src="C:/Users/João Vitor/IdeaProjects/CarteiraAcao/FrontEnd/Imagens/FundoLoginRegistro.png",
    )