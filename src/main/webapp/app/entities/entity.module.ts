import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'entreprise',
                loadChildren: './entreprise/entreprise.module#JhipsterEntrepriseModule'
            },
            {
                path: 'compte-bancaire',
                loadChildren: './compte-bancaire/compte-bancaire.module#JhipsterCompte_bancaireModule'
            },
            {
                path: 'employe',
                loadChildren: './employe/employe.module#JhipsterEmployeModule'
            },
            {
                path: 'contrat',
                loadChildren: './contrat/contrat.module#JhipsterContratModule'
            },
            {
                path: 'produit',
                loadChildren: './produit/produit.module#JhipsterProduitModule'
            },
            {
                path: 'commande',
                loadChildren: './commande/commande.module#JhipsterCommandeModule'
            },
            {
                path: 'commande',
                loadChildren: './commande/commande.module#JhipsterCommandeModule'
            },
            {
                path: 'produit',
                loadChildren: './produit/produit.module#JhipsterProduitModule'
            },
            {
                path: 'chauffeur',
                loadChildren: './chauffeur/chauffeur.module#JhipsterChauffeurModule'
            },
            {
                path: 'vehicule',
                loadChildren: './vehicule/vehicule.module#JhipsterVehiculeModule'
            },
            {
                path: 'permis-de-conduit',
                loadChildren: './permis-de-conduit/permis-de-conduit.module#JhipsterPermis_de_conduitModule'
            },
            {
                path: 'chauffeur',
                loadChildren: './chauffeur/chauffeur.module#JhipsterChauffeurModule'
            },
            {
                path: 'chauffeur',
                loadChildren: './chauffeur/chauffeur.module#JhipsterChauffeurModule'
            },
            {
                path: 'permis-de-conduit',
                loadChildren: './permis-de-conduit/permis-de-conduit.module#JhipsterPermis_de_conduitModule'
            },
            {
                path: 'chauffeur',
                loadChildren: './chauffeur/chauffeur.module#JhipsterChauffeurModule'
            },
            {
                path: 'permis-de-conduit',
                loadChildren: './permis-de-conduit/permis-de-conduit.module#JhipsterPermis_de_conduitModule'
            },
            {
                path: 'commande',
                loadChildren: './commande/commande.module#JhipsterCommandeModule'
            },
            {
                path: 'produit',
                loadChildren: './produit/produit.module#JhipsterProduitModule'
            },
            {
                path: 'commande-produit',
                loadChildren: './commande-produit/commande-produit.module#JhipsterCOMMANDE_PRODUITModule'
            },
            {
                path: 'produit',
                loadChildren: './produit/produit.module#JhipsterProduitModule'
            },
            {
                path: 'commande',
                loadChildren: './commande/commande.module#JhipsterCommandeModule'
            },
            {
                path: 'commande-produit',
                loadChildren: './commande-produit/commande-produit.module#JhipsterCOMMANDE_PRODUITModule'
            },
            {
                path: 'compte-bancaire',
                loadChildren: './compte-bancaire/compte-bancaire.module#JhipsterCompte_bancaireModule'
            },
            {
                path: 'entreprise',
                loadChildren: './entreprise/entreprise.module#JhipsterEntrepriseModule'
            },
            {
                path: 'entreprise',
                loadChildren: './entreprise/entreprise.module#JhipsterEntrepriseModule'
            },
            {
                path: 'entreprise',
                loadChildren: './entreprise/entreprise.module#JhipsterEntrepriseModule'
            },
            {
                path: 'entreprise',
                loadChildren: './entreprise/entreprise.module#JhipsterEntrepriseModule'
            },
            {
                path: 'entreprise',
                loadChildren: './entreprise/entreprise.module#JhipsterEntrepriseModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterEntityModule {}
