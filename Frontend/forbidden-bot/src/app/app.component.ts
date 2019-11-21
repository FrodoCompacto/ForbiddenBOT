import { AuthService } from './login/auth.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Forbidden Bot';

  public status: String = 'processing';

  constructor(private authService :AuthService, private router: Router){}

  ngOnInit(){
    this.authService.userAuthenticatedEmitter.subscribe(
        showMenu => {if (showMenu) {
          this.status = "logged";
        } else this.status = "unlogged";
      }
    );
    this.verifyUser();
  }

  verifyUser(){
    let token = sessionStorage.getItem("token");
    if (status != "logged" && token != null ){
      this.authService.refresh(token).subscribe(response => {
        this.authService.successfulLogin(response.headers.get('Authorization'));
        this.status = "logged";
      },
      error => {
        this.status = "unlogged";
      });
    } else setTimeout(() => this.status = "unlogged", 1000);
  }

  onLogout(){
    this.authService.logout();
    this.status = "unlogged";
  }
}
