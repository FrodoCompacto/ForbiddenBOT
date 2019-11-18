import { AboutComponent } from './about/about.component';
import { DonateComponent } from './donate/donate.component';
import { LoginComponent } from './login/login.component';
import { UploadComponent } from './upload/upload.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';


const routes: Routes = [
  { path: 'admin', loadChildren: './admin/admin.module#AdminModule', canActivate: [AuthGuard] },
  { path: 'upload', component: UploadComponent},
  { path: 'login', component: LoginComponent },
  { path: 'donate', component: DonateComponent},
  { path: 'about', component: AboutComponent},
  { path: '', pathMatch: 'full', redirectTo: 'upload'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
