import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { AdminComponent } from './admin.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { ListComponent } from './list/list.component';
import { VerifyComponent } from './verify/verify.component';


@NgModule({
  declarations: [AdminComponent, ListComponent, VerifyComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    BsDropdownModule.forRoot()
  ]
})
export class AdminModule { }
