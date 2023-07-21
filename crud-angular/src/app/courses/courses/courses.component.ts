import { CoursesService } from './../service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { Observable, catchError, of } from 'rxjs';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]>;
  displayedColumns = ['name', 'category']

  constructor(private coursesService: CoursesService) {

    //this.coursesService.list().subscribe(coursesObs => this.courses = coursesObs);
    this.courses$ = this.coursesService.list().pipe(
      catchError(error => {
        console.log(error);
        return of([])
      })
    )
  }

  ngOnInit(): void {

  }
}
