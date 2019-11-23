import { PartType } from './enums/parttype';

export class NewExodiaPart {
  
  public uploader: string;
  public type: PartType;
  public isLeftOriented: boolean;
  public imageStr: string;
  public captchaResponse: string;

  public NewExodiaPart(){
  }
}

