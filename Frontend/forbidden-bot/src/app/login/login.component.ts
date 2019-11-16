import { User } from './../models/user';
import { AuthService } from './auth.service';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BsModalService, BsModalRef } from 'ngx-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  modalRef: BsModalRef;

  constructor(private authService: AuthService,private modalService: BsModalService) { }

  ngOnInit() {
  }

  login(form: FormGroup, template: TemplateRef<any>) {
    let user: User = form.value;
    this.authService.authUser(user).subscribe(response => {
      this.authService.successfulLogin(response.headers.get('Authorization'));
      this.modalService.show(template);
    });
  }

  hideModal(){
    this.modalService.hide;
  }

}
