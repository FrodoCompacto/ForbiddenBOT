import { GetService } from './../get.service';
import { ExodiaPart } from './../../models/exodiapart';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { BsModalService } from 'ngx-bootstrap';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.scss']
})
export class VerifyComponent implements OnInit {

  @ViewChild('modal', {static: false})
  modal: ElementRef;

  constructor(private getService: GetService, private modalService: BsModalService) { }
  public partsArray: Array<ExodiaPart>;
  public pages: Array<number>;

  public selectMenu: boolean = true;
  public type: String;
  public actualPage: number = 0;

  ngOnInit() {
  }

  getUnverifiedArms(page: number){
    this.getService.getUnverifiedArms(page,20).subscribe(
      data=>{
        this.partsArray = data['content'];
        this.pages = new Array(data['totalPages']);
        this.selectMenu = false;
        if (this.partsArray.length == 0) {
          this.modalService.show(this.modal);
          this.selectMenu = true;
        }
      },
      error=>{
        console.error(error);
      }
    );
  }

  getUnverifiedLegs(page: number){
    this.getService.getUnverifiedLegs(page,20).subscribe(
      data=>{
        this.partsArray = data['content'];
        this.pages = new Array(data['totalPages']);
        this.selectMenu = false;
        if (this.partsArray.length == 0) {
          this.modalService.show(this.modal);
          this.selectMenu = true;
        }
      },
      error=>{
        console.error(error);
      }
    );
  }

  getUnverifiedHeads(page: number){
    this.getService.getUnverifiedHeads(page,20).subscribe(
      data=>{
        this.partsArray = data['content'];
        this.pages = new Array(data['totalPages']);
        this.selectMenu = false;
        if (this.partsArray.length == 0) {
          this.modalService.show(this.modal);
          this.selectMenu = true;
        }
      },
      error=>{
        console.error(error);
      }
    );
  }

  returnOrientation(leftOriented: boolean): string{
    if (leftOriented) return "Left Oriented";
    else return "Right Oriented";
  }

  changePage(page: number){
    if (page >= 0 && page < this.pages.length){
      if (this.type == "ARMS") this.getUnverifiedArms(page);
      else if (this.type == "LEGS") this.getUnverifiedLegs(page);
      else this.getUnverifiedHeads(page);
      this.actualPage = page;
    }
  }

  verify(){
    let verifiedList: Array<number> = new Array();
    let delList: Array<number> = new Array();

    
    this.partsArray.forEach(element => {
      if (element.isVerified){
        verifiedList.push(element.id);
      } else delList.push(element.id);
    });

    if (verifiedList.length > 0){
      this.getService.verify(verifiedList).subscribe( 
        respose => {
          if (delList.length > 0) this.getService.delete(delList).subscribe( x => {
            this.changePage(this.actualPage);
          });
          this.changePage(this.actualPage);
        }
      );
  }
  }

}
