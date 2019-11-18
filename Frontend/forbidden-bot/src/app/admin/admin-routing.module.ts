import { AdminComponent } from './admin.component';
import { VerifyComponent } from './verify/verify.component';
import { ListComponent } from './list/list.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: '', component: AdminComponent, children: [
    { path: 'list', component: ListComponent},
    { path: 'verify', component: VerifyComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
