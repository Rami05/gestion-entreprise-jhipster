import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChauffeur } from 'app/shared/model/chauffeur.model';
import { ChauffeurService } from './chauffeur.service';

@Component({
    selector: 'jhi-chauffeur-delete-dialog',
    templateUrl: './chauffeur-delete-dialog.component.html'
})
export class ChauffeurDeleteDialogComponent {
    chauffeur: IChauffeur;

    constructor(
        protected chauffeurService: ChauffeurService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.chauffeurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'chauffeurListModification',
                content: 'Deleted an chauffeur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-chauffeur-delete-popup',
    template: ''
})
export class ChauffeurDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ chauffeur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ChauffeurDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.chauffeur = chauffeur;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/chauffeur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/chauffeur', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
