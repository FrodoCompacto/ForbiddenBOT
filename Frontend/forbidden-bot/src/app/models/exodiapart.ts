import { PartType } from './enums/parttype';

export class ExodiaPart {
  
  public id: number;
  public image: string;
  public uploader: string;
  public uploadDate: string;
  public type: PartType;
  public isVerified: boolean;

}

