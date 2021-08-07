import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClientesService } from 'src/app/clientes.service';
import { Cliente } from '../cliente';

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styleUrls: ['./clientes-form.component.css']
})
export class ClientesFormComponent implements OnInit {

  cliente : Cliente;
  success : boolean = false;
  errors: String[] = [];
  clienteId : number | undefined;

  constructor( 
    private service : ClientesService,
    private router : Router,
    private activatedRoute : ActivatedRoute
    ) { 
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    let params = this.activatedRoute.params;
    params.subscribe(param => {
      if (param.id) {
        this.clienteId = param.id; 
        this.service.getClienteById(param.id)
          .subscribe( response => {
            this.cliente = response;          
        }, errorResponse => {
          this.cliente = new Cliente();
        }
        );
      }
      
    });
    
  }

  onSubmit(){

    if (this.clienteId != null) {
      console.log(this.clienteId);
      this.service.update(this.cliente)
        .subscribe( response => {
          this.success = true;
          this.errors = [];
        }, errorResponse => {
          this.success = false;
          this.errors = ["Erro ao atualizar o cliente."];
        }
        
        );
    } else {
    
      this.cliente.id = undefined;
      this.cliente.dataCadastro = undefined;
      this.service.salvar(this.cliente)
        .subscribe( response =>{
          this.success = true;
          this.errors = [];
          this.cliente = response;
        }, errorResponse => {
          this.success = false;
          this.cliente.id = undefined;
          this.errors = errorResponse.error.errors;   
        })
    }
  }

  voltarParaListaClientes(){
    this.router.navigate(["/clientes-lista"])
  }

}
