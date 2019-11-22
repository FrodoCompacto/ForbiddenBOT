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

  public partsArray: Array<ExodiaPart>;
  public pages: Array<number>;
  public actualPage: number = 0;


  ngOnInit() {
    this.getVerifiedParts(0);
  }

  getVerifiedParts(page: number) {
    this.getService.getVerifiedParts(page, 20).subscribe(
      data => {
        this.partsArray = data["content"];
        this.pages = new Array(data["totalPages"]);
        if (this.partsArray.length == 0) {
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
}
