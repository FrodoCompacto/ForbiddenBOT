import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { VerifyComponent } from './verify/verify.component';
import { ListsComponent } from './lists/lists.component';


@NgModule({
  declarations: [VerifyComponent, ListsComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
