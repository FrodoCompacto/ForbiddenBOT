import { GetService } from './../get.service';
import { Admin } from './../../models/Admin';
import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public processing: boolean = false;

  constructor(private getService: GetService,private modalService: BsModalService, private router: Router) { }

  ngOnInit() {
  }


  login(form, templateSuccess: TemplateRef<any>, templateFail: TemplateRef<any>) {
    this.processing = true;
    let admin: Admin = form.value;
    this.getService.insertNewAdm(admin).subscribe(response => {
      this.modalService.show(templateSuccess);
      this.router.navigate(['/upload']);
      this.processing = false
    },
    error => {
      this.modalService.show(templateFail);
      this.processing = false
    }
    );
  }

}
