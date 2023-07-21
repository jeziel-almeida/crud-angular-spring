import { CoursesService } from './../service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../model/course';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]>;
  displayedColumns = ['name', 'category']

  constructor(
    private coursesService: CoursesService,
    public dialog: MatDialog
  ) {

    //this.coursesService.list().subscribe(coursesObs => this.courses = coursesObs);
    this.courses$ = this.coursesService.list().pipe(
      catchError(error => {
        this.onError(`Erro ao carregar cursos!\n${error.status}: ${error.statusText}.`)
        return of([])
      })
    )
  }

  ngOnInit(): void {

  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }
}
