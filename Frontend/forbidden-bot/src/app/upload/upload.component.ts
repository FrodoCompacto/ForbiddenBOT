import { PostService } from './post.service';
import { NewExodiaPart } from './../models/newexodiapart';
import { Component, OnInit, TemplateRef, ViewChild, ElementRef } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap';
import { PartType } from '../models/enums/parttype';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {

  @ViewChild('myInput', {static: false})
  myInputVariable: ElementRef;

  public processing: boolean = false;
  public exPart: NewExodiaPart = new NewExodiaPart();
  public image;
  public message: String;


  constructor(private modalService: BsModalService, private postService: PostService) { }

  ngOnInit() {
    this.exPart.isLeftOriented = false;
  }

  send(templateSuccess: TemplateRef<any>, templateFail: TemplateRef<any>) {
    this.exPart.imageStr = this.image;

    if (this.exPart.type != PartType.ARM && this.exPart.type != PartType.LEG && this.exPart.type != PartType.HEAD){
      this.message = "Part type must be ARM, LEG or HEAD.";
      this.modalService.show(templateFail);
    } else if ( this.exPart.type != PartType.HEAD && this.exPart.isLeftOriented == null) {
      this.message = "An arm or leg should be oriented.";
      this.modalService.show(templateFail);
    } else if ( !this.exPart.imageStr ){
      this.message = "Please select an valid image.";
      this.modalService.show(templateFail);
    } else {
      this.processing = true;

    this.postService.send(this.exPart).subscribe(response => {
      this.modalService.show(templateSuccess);
      this.processing = false;
      this.exPart = new NewExodiaPart();
      this.exPart.isLeftOriented = false;
      this.myInputVariable.nativeElement.value = "";
    },
    error => {
      this.message = "Request not sent, please try again.";
      this.modalService.show(templateFail);
      this.processing = false;
    }
    );
  }
  }

changeListener($event) : void {
  this.readThis($event.target);
}

readThis(inputValue: any): void {
  var file:File = inputValue.files[0];
  var myReader:FileReader = new FileReader();

  myReader.onloadend = (e) => {
    this.image = myReader.result;
  }
  myReader.readAsDataURL(file);
} 

resetOrientation(){
  if ( this.exPart.type == PartType.HEAD ){
    this.exPart.isLeftOriented == true;
  }
}

}
