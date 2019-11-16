export class Credentials {
  public authStatus: boolean;
  public token: string;

  Credential(){

    this.authStatus = false;
    this.token = null;
  }
}
