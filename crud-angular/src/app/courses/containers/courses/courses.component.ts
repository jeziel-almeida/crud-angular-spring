import { CoursesService } from '../../service/courses.service';
import { Component, OnInit } from '@angular/core';
import { Course } from '../../model/course';
import { Observable, catchError, of } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.scss']
})
export class CoursesComponent implements OnInit {

  courses$: Observable<Course[]> | null = null;

  constructor(
    private service: CoursesService,
    public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.refresh();
  }

  ngOnInit(): void {}

  refresh() {
    this.courses$ = this.service.list().pipe(
      catchError(error => {
        this.onError(`Erro ao carregar cursos!\n${error.status}: ${error.statusText}.`)
        return of([])
      })
    )
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg
    });
  }

  onAdd() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  onEdit(course: Course) {
    this.router.navigate(['edit', course.id], {relativeTo: this.route});
  }

  onDelete(course: Course) {
    this.service.remove(course.id).subscribe({
      next: () => {
        this.snackBar.open('Removido com sucesso!', 'X', {
          duration: 2000,
          verticalPosition: 'top',
          horizontalPosition: 'center'
        })
        this.refresh();
      },
      error: () => this.onError('Erro ao remover curso!')
    })
  }
}
