import { AuthService } from './login/auth.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ModalModule } from 'ngx-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UploadComponent } from './upload/upload.component';
import { AboutComponent } from './about/about.component';
import { DonateComponent } from './donate/donate.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './guards/auth.guard';


@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    AboutComponent,
    DonateComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ModalModule.forRoot()
  ],
  providers: [AuthService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
