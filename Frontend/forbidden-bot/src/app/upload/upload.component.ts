import { PostService } from "./post.service";
import { NewExodiaPart } from "./../models/newexodiapart";
import {
  Component,
  OnInit,
  TemplateRef,
  ViewChild,
  ElementRef
} from "@angular/core";
import { BsModalService } from "ngx-bootstrap";
import { PartType } from "../models/enums/parttype";
import { ImageCroppedEvent } from "ngx-image-cropper";

@Component({
  selector: "app-upload",
  templateUrl: "./upload.component.html",
  styleUrls: ["./upload.component.scss"]
})
export class UploadComponent implements OnInit {
  @ViewChild("myInput", { static: false })
  myInputVariable: ElementRef;

  public imageChangedEvent: any = "";
  public croppedImage: any = "";

  public processing: boolean = false;
  public exPart: NewExodiaPart = new NewExodiaPart();
  public message: String;

  constructor(
    private modalService: BsModalService,
    private postService: PostService
  ) {}

  ngOnInit() {
    this.exPart.isLeftOriented = false;
  }

  send(templateSuccess: TemplateRef<any>, templateFail: TemplateRef<any>) {
    this.exPart.imageStr = this.croppedImage;

    if (
      this.exPart.type != PartType.ARM &&
      this.exPart.type != PartType.LEG &&
      this.exPart.type != PartType.HEAD
    ) {
      this.message = "Part type must be ARM, LEG or HEAD.";
      this.modalService.show(templateFail);
    } else if (
      this.exPart.type != PartType.HEAD &&
      this.exPart.isLeftOriented == null
    ) {
      this.message = "An arm or leg should be oriented.";
      this.modalService.show(templateFail);
    } else if (
      this.exPart.uploader != null &&
      this.exPart.uploader.length > 15
    ) {
      this.message = "Nickname must be 15 characters or less.";
      this.modalService.show(templateFail);
    } else if (!this.exPart.imageStr || this.exPart.imageStr.length == "".length) {
      this.message = "Please select an valid image.";
      this.modalService.show(templateFail);
    } else if (this.exPart.captchaResponse == null) {
      this.message = "Please resolve the captcha.";
      this.modalService.show(templateFail);
    } else {
      this.processing = true;

      this.postService.send(this.exPart).subscribe(
        response => {
          this.modalService.show(templateSuccess);
          this.processing = false;
          this.exPart = new NewExodiaPart();
          this.exPart.isLeftOriented = false;
          this.myInputVariable.nativeElement.value = "";
          window.location.reload();
        },
        error => {
          this.message = "Request not sent, please try again.";
          this.modalService.show(templateFail);
          this.processing = false;
        }
      );
    }
  }

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
  }
  loadImageFailed(templateFail: TemplateRef<any>) {
    this.message = "Wrong file type was selected (only png and jpg are allowed).";
      this.modalService.show(templateFail);
  }
  
  resolved(captchaResponse: string) {
    this.exPart.captchaResponse = captchaResponse;
  }
}
