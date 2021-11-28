import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor (private http: HttpClient) { }


  login(user: string, pass: string) {

    const auth = "Basic " + btoa(user + ":" + pass);
    let headers = new HttpHeaders({
      'Authorization': auth,
      'X-Requested-With': 'XMLHttpRequest'
    });

    const httpOptions = {
      headers: headers,
      withCredentials: true,
      observe: 'response' as 'response'
    };

    this.http.get("http://localhost:8080/app/login", httpOptions).subscribe((resp: any) => {

      const xsrf = resp.headers.get('CSRF-TOKEN-VALUE');
      console.log(xsrf);
      window.sessionStorage.setItem("XSRF-TOKEN", xsrf);

      console.log(resp);

      window.alert('Success');
    }, (err) => {
      window.alert('Failure');
    });
  }


  printCookie() {

    let headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest'
    });

    const httpOptions = {
      headers: headers,
      withCredentials: true,
      observe: 'response' as 'response'
    };

    this.http.get("http://localhost:8080/app/cookie", httpOptions).subscribe((resp: any) => {
      console.log(resp);
      const xsrf = resp.headers.get('CSRF-TOKEN-VALUE');
      console.log(xsrf);

      window.alert('Success');
    }, (err) => {
      window.alert('Failure');
    });
  }


  getData() {

    let headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest'
    });

    const httpOptions = {
      headers: headers,
      withCredentials: true,
      observe: 'response' as 'response'
    };

    this.http.get("http://localhost:8080/app/cookie", httpOptions).subscribe((resp: any) => {
      console.log(resp);

      window.alert('Success');
    }, (err) => {
      window.alert('Failure');
    });
  }

  printCookiePostWithCSRFHeader() {

    const xsrf = sessionStorage.getItem('XSRF-TOKEN') + '';
    let headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest',
      'X-CSRF-TOKEN': xsrf
    });

    const httpOptions = {
      headers: headers,
      withCredentials: true,
      observe: 'response' as 'response'
    };

    this.http.post("http://localhost:8080/app/csrf", {}, httpOptions).subscribe((resp: any) => {
      console.log(resp);

      window.alert('Success');
    }, (err) => {
      window.alert('Failure');
    });

  }
  printCookiePost() {

    let headers = new HttpHeaders({
      'X-Requested-With': 'XMLHttpRequest'
    });

    const httpOptions = {
      headers: headers,
      withCredentials: true,
      observe: 'response' as 'response'
    };

    this.http.post("http://localhost:8080/app/csrf", {}, httpOptions).subscribe((resp: any) => {
      console.log(resp);

      window.alert('Success');
    }, (err) => {
      window.alert('Failure');
    });
  }

  getCookie() {
    let cookie: any;
    document.cookie.split(';').forEach(function (el) {
      let [k, v] = el.split('=');
      cookie[k.trim()] = v;
    });

    console.log(cookie);
  }

}
