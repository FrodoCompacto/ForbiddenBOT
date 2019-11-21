import { NewExodiaPart } from './../models/newexodiapart';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {


  constructor() { }

  ngOnInit() {
  }

  login(form: FormGroup, templateSuccess: TemplateRef<any>, templateFail: TemplateRef<any>) {
  
  }

}
