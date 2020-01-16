import { FormsModule } from '@angular/forms';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { AdminComponent } from './admin.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaginationModule } from 'ngx-bootstrap/pagination';
import { ModalModule } from 'ngx-bootstrap/modal';

import { AdminRoutingModule } from './admin-routing.module';
import { ListComponent } from './list/list.component';
import { VerifyComponent } from './verify/verify.component';
import { RegisterComponent } from './register/register.component';


@NgModule({
  declarations: [AdminComponent, ListComponent, VerifyComponent, RegisterComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    BsDropdownModule.forRoot(),
    PaginationModule.forRoot(),
    ModalModule.forRoot(),
    FormsModule
    
  ]
})
export class AdminModule { }
