import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessDialogComponent } from 'src/app/shared/components/success-dialog/success-dialog.component';

import { CoursesService } from '../../service/courses.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from '../../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent {

  form = this.formBuilder.group({
    id: [''],
    name: [''],
    category: ['']
  })

  constructor(
    private formBuilder: NonNullableFormBuilder,
    public dialog: MatDialog,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private router: Router,
    private route: ActivatedRoute
  ) {

  }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];

    this.form.setValue({
      id: course.id,
      name: course.name,
      category: course.category
    })

  }

  onSubmit() {
    //https://rxjs.dev/deprecations/subscribe-arguments
    this.service.save(this.form.value).subscribe({next: (result) => this.onSuccess(), error: (error) => this.onError()})
    //const result$ = this.service.save(this.form.value);
    //result$.subscribe({next: (result) => this.onSuccess(), error: (error) => this.onError()})

  }

  private onSuccess() {
    const successMsg = "Curso salvo com sucesso!";
    this.dialog.open(SuccessDialogComponent, {
      data: successMsg
    });
    this.router.navigate(['courses'])
  }

  private onError() {
    this.snackBar.open("Erro ao salvar o curso!", "", {duration: 3000})
  }

  goBack() {
    this.location.back();
  }
}
