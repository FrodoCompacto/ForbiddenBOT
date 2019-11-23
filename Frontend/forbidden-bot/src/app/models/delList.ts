import { ExodiaPart } from './exodiapart';
import { PartType } from './enums/parttype';

export class DelList {
  
  public id: number;
  public image: string;
  public uploader: string;
  public uploadDate: string;
  public type: PartType;
  public marked: boolean = false;

}

