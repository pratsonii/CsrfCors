import { HttpService } from './http.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'CsrfCorsUI';

  user = '';
  pass = '';


  constructor (private http: HttpService) {
    // this.http.getData();
  }

  login() {
    this.http.login(this.user, this.pass);
  }

  printCookie() {
    this.http.printCookie();
  }

  printCookiePost() {
    this.http.printCookiePost();
  }

  printCookiePostWithCSRFHeader() {
    this.http.printCookiePostWithCSRFHeader();
  }

}
