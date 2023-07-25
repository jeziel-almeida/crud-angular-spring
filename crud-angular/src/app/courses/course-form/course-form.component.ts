import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder, private router: Router) {
    this.form = formBuilder.group({
      name: [null],
      category: [null]
    })
  }

  onSubmit() {

  }

  onCancel() {
    this.router.navigate(['/courses']);
  }
}
