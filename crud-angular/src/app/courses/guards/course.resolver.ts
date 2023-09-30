import { ActivatedRouteSnapshot, ResolveFn, RouterStateSnapshot } from "@angular/router";
import { Course } from "../model/course";
import { Observable, of } from "rxjs";
import { CoursesService } from "../service/courses.service";
import { inject } from "@angular/core";

export const CourseResolver: ResolveFn<Course> = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot,
  service: CoursesService = inject(CoursesService)

): Observable<Course> => {

  if(route.params && route.params['id']) {
    return service.loadById(route.params['id']);
  }
  return of({_id: '', name: '', category: '', lessons: []});
}
