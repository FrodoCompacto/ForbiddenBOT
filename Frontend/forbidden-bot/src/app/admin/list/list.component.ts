import { ExodiaPart } from './../../models/exodiapart';
import { GetService } from './../get.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  constructor(private getService: GetService) { }
  public partsArray: Array<ExodiaPart>;
  public pages: Array<number>;
  
  public selectMenu:boolean = true;

  ngOnInit() {
  }

  getUnverifiedParts(){
    this.getService.getUnverifiedParts(0,20).subscribe(
      data=>{
        this.partsArray = data['content'];
        this.pages = new Array(data['totalPages']);
        this.selectMenu = false;
      },
      error=>{
        console.error(error);
      }
    );
  }

  getVerifiedParts(){
    this.getService.getVerifiedParts(0,20).subscribe(
      data=>{
        this.partsArray = data['content'];
        this.pages = new Array(data['totalPages']);
        this.selectMenu = false;
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

}