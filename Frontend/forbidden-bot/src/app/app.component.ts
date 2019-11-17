import { AuthService } from './login/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Forbidden Bot';

  public showAdminMenu: boolean = false;

  constructor(private authService :AuthService){}

  ngOnInit(){
    this.authService.userAuthenticated.subscribe(
        showMenu => this.showAdminMenu = showMenu
    );
    this.verifyUser();
  }

  verifyUser(){
    let token = sessionStorage.getItem("token");
    if (!this.showAdminMenu && token != null ){
      this.authService.refresh(token).subscribe(response => {
        this.authService.successfulLogin(response.headers.get('Authorization'));
      });
    }
  }

  onLogout(){
    this.authService.logout();
  }
}
