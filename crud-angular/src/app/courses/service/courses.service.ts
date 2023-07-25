import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Course } from '../model/course';
import { Observable, delay, first, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private httpClient: HttpClient) { }

  private readonly API_URL = 'api/courses';

  list(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(this.API_URL).pipe(
      first(),
      //delay(2000),
      tap(courses => console.log(courses))
    );
  }
}
