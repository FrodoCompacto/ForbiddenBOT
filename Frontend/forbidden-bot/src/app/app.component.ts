import { AuthService } from './login/auth.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Forbidden Bot';

  showAdminMenu: boolean = false;

  constructor(private authService :AuthService){}

  ngOnInit(){
    this.authService.userAuthenticated.subscribe(
        showMenu => this.showAdminMenu = showMenu
    );
  }
}
