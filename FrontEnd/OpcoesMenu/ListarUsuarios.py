import requests
import flet as ft
from session import session

def listUsuario_page(on_login):
    headers = {"Authorization": "Bearer " + session.user_data.get("codigo", "")}
    
    try:
        response = requests.get("http://localhost:8080/credenciais/todos", headers=headers)
        
        response.raise_for_status()  
        data = response.json()
    except requests.exceptions.RequestException as e:
        data = []
        print(f"Erro ao buscar usuários: {e}")

    columns = [
        ft.DataColumn(ft.Text("Codigo")),
        ft.DataColumn(ft.Text("Nome")),
        ft.DataColumn(ft.Text("Senha"))
    ]

    rows = [
        ft.DataRow(
            cells=[
                ft.DataCell(ft.Text(credencial["codigo"])),
                ft.DataCell(ft.Text(credencial["nome"])),
                ft.DataCell(ft.Text(credencial["senha"]))
            ]
        ) for credencial in data
    ]

    data_table = ft.DataTable(columns=columns, rows=rows, border=ft.border.all(1))

    return ft.Container(
        content=ft.Column(
            [   
                ft.Column([data_table], scroll=ft.ScrollMode.ALWAYS, height=130),
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