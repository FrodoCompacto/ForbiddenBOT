import { User } from './../models/user';
import { AuthService } from './auth.service';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public processing: boolean = false;

  constructor(private authService: AuthService,private modalService: BsModalService, private router: Router) { }

  ngOnInit() {
  }

  login(form: FormGroup, templateSuccess: TemplateRef<any>, templateFail: TemplateRef<any>) {
    this.processing = true;
    let user: User = form.value;
    this.authService.authUser(user).subscribe(response => {
      this.authService.successfulLogin(response.headers.get('Authorization'));
      this.modalService.show(templateSuccess);
      this.router.navigate(['/upload']);
    }, // success path
    error => {
      this.modalService.show(templateFail);
    }
    );
    setTimeout(() => this.processing = false, 1000);
  }


}
