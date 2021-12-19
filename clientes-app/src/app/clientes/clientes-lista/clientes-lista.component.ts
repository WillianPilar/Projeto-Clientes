import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-lista',
  templateUrl: './clientes-lista.component.html',
  styleUrls: ['./clientes-lista.component.css']
})
export class ClientesListaComponent implements OnInit {

  clientes: Cliente[] = [];
  clienteSelecionado : Cliente = new Cliente();

  constructor(
    private service : ClientesService,
    private router : Router
    ) { }

  ngOnInit(): void {
    this.getAllClientes();
  }

  getAllClientes(){
    this.service.getClientes()
      .subscribe( resposta => this.clientes = resposta);
  }

  novoCadastro(){
    this.router.navigate(["/clientes-form"]);
  }

  preparaDelecao(cliente : Cliente){
    this.clienteSelecionado = cliente;
  }

  deletarCliente(){
    console.log(this.clienteSelecionado);
    this.service.deletarCliente(this.clienteSelecionado)
      .subscribe(response => {

        this.getAllClientes();
        this.clienteSelecionado = new Cliente();

    });
  }

}
