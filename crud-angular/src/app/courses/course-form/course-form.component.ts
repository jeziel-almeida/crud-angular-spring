import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SuccessDialogComponent } from 'src/app/shared/components/success-dialog/success-dialog.component';

import { CoursesService } from '../service/courses.service';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    public dialog: MatDialog,
    private service: CoursesService,
    private snackBar: MatSnackBar
  ) {
    this.form = formBuilder.group({
      name: [null],
      category: [null]
    })
  }

  onSuccess(successMsg: string) {
    this.dialog.open(SuccessDialogComponent, {
      data: successMsg
    });
  }

  onSubmit() {
    this.service.save(this.form.value).subscribe(result => console.log("Result: ", result), error => this.onError())

    //this.onSuccess("Curso salvo com sucesso!")
  }

  onCancel() {
    this.router.navigate(['/courses']);
  }

  private onError() {
    this.snackBar.open("Erro ao salvar o curso!", "", {duration: 3000})
  }
}
