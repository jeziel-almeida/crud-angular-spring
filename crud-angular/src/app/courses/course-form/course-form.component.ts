import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SuccessDialogComponent } from 'src/app/shared/components/success-dialog/success-dialog.component';

import { CoursesService } from '../service/courses.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private router: Router
  ) {
    this.form = formBuilder.group({
      name: [null],
      category: [null]
    })
  }

  onSuccess() {
    const successMsg = "Curso salvo com sucesso!";

    this.dialog.open(SuccessDialogComponent, {
      data: successMsg
    });

    this.router.navigate(['courses'])
  }

  onSubmit() {
    //https://rxjs.dev/deprecations/subscribe-arguments
    this.service.save(this.form.value).subscribe({next: (result) => this.onSuccess(), error: (error) => this.onError()})


  }

  goBack() {
    this.location.back();
  }

  private onError() {
    this.snackBar.open("Erro ao salvar o curso!", "", {duration: 3000})
  }
}
