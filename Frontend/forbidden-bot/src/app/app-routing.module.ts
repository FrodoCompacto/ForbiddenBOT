import { AdminModule } from './admin/admin.module';
import { AboutComponent } from './about/about.component';
import { DonateComponent } from './donate/donate.component';
import { LoginComponent } from './login/login.component';
import { UploadComponent } from './upload/upload.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'upload', component: UploadComponent},
  { path: 'login', component: LoginComponent},
  { path: 'donate', component: DonateComponent},
  { path: 'about', component: AboutComponent},
  { path: 'admin', component: AdminModule},
  { path: '', pathMatch: 'full', redirectTo: 'upload'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
