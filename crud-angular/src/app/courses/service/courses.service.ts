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
      //tap(courses => console.log(courses))
    );
  }

  loadById(id: string) {
    return this.httpClient.get<Course>(`${this.API_URL}/${id}`);
  }

  save(record: Partial<Course>) {

    if(record.id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private create(record: Partial<Course>) {
    return this.httpClient.post<Course>(this.API_URL, record);
  }

  private update(record: Partial<Course>) {
    return this.httpClient.put<Course>(`${this.API_URL}/${record.id}`, record);
  }
}
