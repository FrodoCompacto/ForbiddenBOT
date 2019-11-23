import { DelList } from './../../models/delList';
import { ExodiaPart } from "./../../models/exodiapart";
import { GetService } from "./../get.service";
import { Component, OnInit, ElementRef, ViewChild } from "@angular/core";
import { BsModalService } from 'ngx-bootstrap';
import { Router } from '@angular/router';

@Component({
  selector: "app-list",
  templateUrl: "./list.component.html",
  styleUrls: ["./list.component.scss"]
})
export class ListComponent implements OnInit {
  constructor(private getService: GetService, private modalService: BsModalService, private router:Router) {}

  @ViewChild('modal', {static: false})
  modal: ElementRef;

  public pages: Array<number>;
  public actualPage: number = 0;

  public delList: Array<DelList>;

  ngOnInit() {
    this.getVerifiedParts(0);
  }

  getVerifiedParts(page: number) {
    this.getService.getVerifiedParts(page, 20).subscribe(
      data => {
        this.delList = data["content"];
        this.pages = new Array(data["totalPages"]);
        if (this.delList.length == 0) {
          this.modalService.show(this.modal);
          this.router.navigate(['/admin/verify']);
        }
      },
      error => {
        console.error(error);
      }
    );
  }

  changePage(page: number) {
    if (page >= 0 && page < this.pages.length) {
      this.getVerifiedParts(page);
      this.actualPage = page;
    }
  }

  delete(){
    let markedItens: Array<number> = new Array();

    this.delList.forEach(element => {
      if (element.marked){
        markedItens.push(element.id);
      }
    });

    if (markedItens.length > 0){
      this.getService.delete(markedItens).subscribe( 
        respose => {
          this.changePage(this.actualPage);
        }
      );
    }
  }
}
