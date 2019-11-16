import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { LoginComponentComponent } from './login-component/login-component.component';
import { LoginComponent } from './login/login.component';
import { VerifyComponent } from './verify/verify.component';
import { ListsComponent } from './lists/lists.component';


@NgModule({
  declarations: [LoginComponentComponent, LoginComponent, VerifyComponent, ListsComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
