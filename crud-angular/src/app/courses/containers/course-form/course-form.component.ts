import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { NonNullableFormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

import { CoursesService } from '../../service/courses.service';
import { ActivatedRoute } from '@angular/router';
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
    this.service.save(this.form.value).subscribe({
      next: (result) => this.onSuccess(), 
      error: (error) => this.onError()
    })
  }

  private onSuccess() {
    this.snackBar.open("Curso salvo com sucesso!", "X", {duration: 4000})
    this.goBack();
  }

  private onError() {
    this.snackBar.open("Erro ao salvar o curso!", "X", {duration: 4000})
  }

  goBack() {
    this.location.back();
  }
}
